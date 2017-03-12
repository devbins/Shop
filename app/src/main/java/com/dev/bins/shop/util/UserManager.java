package com.dev.bins.shop.util;

import android.content.Context;

import com.dev.bins.shop.bean.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by bin on 12/03/2017.
 */

public class UserManager {
    private static String USERFILENAME = "user.json";

    public static void save(Context context, User user) {
        OutputStreamWriter writer = null;
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(USERFILENAME, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(fileOutputStream);
            Gson gson = new Gson();
            String s = gson.toJson(user);
            writer.write(s);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != writer) {
                    writer.flush();
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static User read(Context context) {
        BufferedReader reader = null;
        try {
            FileInputStream fileInputStream = context.openFileInput(USERFILENAME);
            StringBuilder sb = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(fileInputStream));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            Gson gson = new Gson();
            User user = gson.fromJson(sb.toString(), User.class);
            return user;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (null != reader) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
