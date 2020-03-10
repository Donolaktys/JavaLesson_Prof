package client;

import java.io.*;
import java.util.ArrayList;

public class History {
    private final String historyDir = System.getProperty("user.home") + "\\chat_history";
    File history;
    private File dir = new File(historyDir);

    // в конструкторе создаем \chat_history\history_userLogin.txt в папке пользователя ОС,
    // если их еще не существует
    public History(String userLogin) {
        history = new File(historyDir + "\\history_" + userLogin + ".txt");
        if (!history.exists()) {
            if (!dir.exists()) {
                dir.mkdir();
            }
            try {
                history.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Невозможно создать файл");
            }
        }
    }

    public void saveHistory(String textArea) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(history))) {
            writer.write(textArea);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String loadHistory() {
//        String msg = null;
//        try (BufferedReader reader = new BufferedReader(new FileReader(history))) {
//            String r;
//            while ((r = reader.readLine()) != null) {
//                if (msg == null) {
//                    msg = r + "\n";
//                }
//                msg += r + "\n";
//            }
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
//        return msg;
//    }
        ArrayList<String> list = null;
        String msg = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(history))) {
            list = new ArrayList<>();

            while ((msg = reader.readLine()) != null) {
                if (!msg.startsWith("History")) {
                    list.add(msg);
                    if (list.size() > 100) {
                        list.remove(0);
                    }
                }
            }

            for (String s :
                    list) {
                if (msg != null) { msg += s + "\n";
                } else {
                    msg = s + "\n"; // для первой строки
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

}