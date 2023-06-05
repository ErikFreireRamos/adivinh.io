package GUI;

import server.protocol.Protocol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoomLabel extends JLabel implements MouseListener, Protocol {
    private String id;

    private Control control;
    private RoomFrame frame;
    private RoomsRefresher scheduler;

    public RoomLabel(String id, String title, Control control, RoomsRefresher scheduler, RoomFrame frame) {
        super(" " + title);

        this.id = id;
        this.frame = frame;
        this.control = control;
        this.scheduler = scheduler;

        this.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        this.setPreferredSize(new Dimension(450, 50));
        this.setBackground(new Color(0x601A80));
        this.setForeground(Color.LIGHT_GRAY);
        this.addMouseListener(this);
        this.setOpaque(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == this) {
           control.setRequest(ENTER_ROOM_STRING+","+id);
           String response = control.getResponse();

           if (response.contains(SUCESSFULL_STRING)) {
               scheduler.shutdown();
           }
            System.out.println(response);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == this) {
            this.setBackground(new Color(0x500A80));
            frame.repaint();
            frame.setVisible(true);

            scheduler.shutdown();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == this) {
            this.setBackground(new Color(0x601A80));
            frame.repaint();
            frame.setVisible(true);

            scheduler.restart();
        }
    }
}
