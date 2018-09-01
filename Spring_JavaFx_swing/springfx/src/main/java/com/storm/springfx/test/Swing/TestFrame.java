package com.storm.springfx.test.Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestFrame  extends JFrame {

    JPanel jPanel;
    JButton button1;

    public TestFrame(){

        this.setTitle("test frame");
        this.setSize(400, 400);


        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jPanel = new JPanel();
        button1 = new JButton("alalalalalalal");
        button1.addMouseListener(new MaListner());
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
//                Lesson27.frame2.dispose();
                new Lesson27();
            }
        });
        jPanel.add(button1);
        this.getContentPane().add(jPanel);
        this.pack();
        this.setVisible(true);
    }
}
