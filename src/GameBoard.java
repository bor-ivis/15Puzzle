import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class GameBoard extends JFrame implements ActionListener {
    Brickor brick = new Brickor();
    JPanel gameBoard = new JPanel();
    JPanel northPanel = new JPanel();
    JPanel brickPanel = new JPanel();
    JPanel southPanel = new JPanel();

    JLabel l = new JLabel("15-PUZZLE GAME BOARD");
    JLabel empty = new JLabel("");

    JButton[] buttons = new JButton[15];
    JButton nyttSpel = new JButton("Nytt spel");
    JButton ezWin = new JButton("Lätt vinst knapp!");

    public GameBoard() {
        setTitle("Femtonspel");
        l.setFont(new Font("Serif", Font.BOLD, 40));
        l.setForeground(Color.RED);

        this.add(gameBoard);
        gameBoard.setLayout(new BorderLayout());
        gameBoard.add(northPanel, BorderLayout.NORTH);
        gameBoard.add(brickPanel, BorderLayout.CENTER);
        gameBoard.add(southPanel, BorderLayout.SOUTH);
        brickPanel.setLayout(new GridLayout(4, 4));
        northPanel.setLayout(new FlowLayout());
        northPanel.add(l);
        southPanel.add(nyttSpel);
        southPanel.add(ezWin);


        for (int i = 0; i < 15; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 20));
            buttons[i].addActionListener(this);
            brickPanel.add(buttons[i]);
        }
        empty.setOpaque(true);
        empty.setBackground(Color.lightGray);
        brickPanel.add(empty);
        nyttSpel.addActionListener(e ->{brick.resetRandom();
            updateBoard();});
        ezWin.addActionListener(e->{brick.lättVinst();
            updateBoard();});


        updateBoard();
        setSize(600,600);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void updateBoard(){
        brickPanel.removeAll();
        int buttonIndex=0;

        for(int i=0; i < 16; i++){
            Tile bricke = brick.getTile(i);
            if(bricke.isEmpty()){
                brickPanel.add(empty);
            }
            else {
                buttons[buttonIndex].setText(bricke.getVärde());
                brickPanel.add(buttons[buttonIndex]);
                buttonIndex++;
            }
        }
        brickPanel.revalidate();
        brickPanel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 15; i++) {
            if (e.getSource() == buttons[i]) {
                for (int j = 0; j < 16; j++){
                    if (brick.getTile(j).getVärde().equals(buttons[i].getText())){
                        if (brick.moveTile(j)){
                            updateBoard();
                            if (brick.isSolved()){
                                JOptionPane.showMessageDialog(null, "Grattis, du vann!");
                            }
                        }
                        return;
                    }
                }
            }
        }
    }
    void main(){}
}









