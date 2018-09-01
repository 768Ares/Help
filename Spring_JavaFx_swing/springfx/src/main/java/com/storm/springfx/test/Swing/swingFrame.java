package com.storm.springfx.test.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class swingFrame {
    private JButton mainButton;
    private JPanel panelMain;
    private JPanel panelMain2;
    private JButton button1;
    private JButton button2;
    private static JFrame frame;

    public swingFrame() {
        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                JOptionPane.showMessageDialog(null, "swing jest \"suuuuper!!!!\"");
                System.out.println("aaaaaaaaaa");
                System.out.println(frame);
                panelMain2 = new JPanel();
                panelMain2.add(button1);
                frame.getContentPane().removeAll();
                frame.getContentPane().add(panelMain2);
                frame.pack();
//                frame.setVisible(true);
//                frame.setContentPane(new swingFrame().panelMain2);

            }

        });
    }

    public static void main(String[] args) {
        frame = new JFrame("app");
        frame.setContentPane(new swingFrame().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
