package com.mcy.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    ExecutorService executorService;
    TextView sendTOClient, receiveFromClient;
    Handler handler = new Handler(Looper.getMainLooper());

    int port = 8000;
//    int port = 9000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        executorService = Executors.newCachedThreadPool();
        String localIpAddress = getIpAddressString();
        Log.d("macy7", "ip=== " + localIpAddress);
        startService();
        sendTOClient = findViewById(R.id.sendClient);
        receiveFromClient = findViewById(R.id.receiveClient);
        findViewById(R.id.send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        OutputStream outputStream = null;
                        try {
                            double random = Math.random();
                            final String toClient = ("你好，我是服务器" + random + "\n");
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    sendTOClient.setText(toClient);
                                }
                            });
                            outputStream = socket.getOutputStream();
                            outputStream.write(toClient.getBytes("utf-8"));
//                            outputStream.close();
                        } catch (IOException e) {
                            Log.d("macy7", "send: " + e);
                        }
                    }
                });


            }
        });

        findViewById(R.id.receive).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executorService.submit(new Runnable() {
                    @Override
                    public void run() {
                        InputStreamReader reader;
                        try {
                            // 获取读取流
                            InputStream inputStream = socket.getInputStream();
                            reader = new InputStreamReader(inputStream);
                            BufferedReader bufferedReader = new BufferedReader(reader);
                            // 读取数据
                            final String msg = bufferedReader.readLine();
                            Log.d("macy7","获取到客户端的信息：" + msg);
//                            inputStream.close();
//                            reader.close();
//                            bufferedReader.close();
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    receiveFromClient.setText(msg);
                                }
                            });
                        } catch (IOException e) {
                            Log.d("macy7", "receive: " + e);
                        }
                    }
                });

            }
        });
    }


    // gets the ip address of your phone's network
    public static String getIpAddressString() {
        try {
            for (Enumeration<NetworkInterface> enNetI = NetworkInterface
                    .getNetworkInterfaces(); enNetI.hasMoreElements(); ) {
                NetworkInterface netI = enNetI.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = netI
                        .getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "0.0.0.0";
    }

    Socket socket = null; //等待客户端连接

    /**
     * 启动服务监听，等待客户端连接
     */
    private void startService() {
        try {
            // 创建ServerSocket
            final ServerSocket serverSocket = new ServerSocket(port);
            Log.d("macy7","--开启服务器，监听端口 9999--");

            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    // 监听端口，等待客户端连接
                    try {
                        Log.d("macy7","--等待客户端连接--");
                        socket = serverSocket.accept();
                        Log.d("macy7","得到客户端连接：" + socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
