import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;

public class PriceChangeController {
    private PriceChangeScreen screen;

    public PriceChangeController() {
        screen = new PriceChangeScreen();
        screen.setVisible(true);
        screen.setResizable(false);
        screen.setSize(817, 438);
        screen.pack();
        screen.setLocationRelativeTo(null);
        screen.getSearchbtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchBtnAction();
            }
        });
        screen.getSubmitbtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitBtnAction();
            }
        });
    }

    private void searchBtnAction() {
        String symbol = screen.getSearchtxt().getText();
        screen.getSymbolTxt().setText(symbol);
        if (!DBaseStock.isExist(symbol)) {
            screen.getNameTxt().setText("This Stock Item is Unavailable");
            screen.getNameTxt().setForeground(new java.awt.Color(255, 204, 204));
        } else {
            screen.getNameTxt().setText(DBaseStock.getStockItem(symbol).getSecurityName());
            screen.getOldPriceTxt().setText("" + DBaseStock.getStockItem(symbol).getPrice());
            screen.getNewPricetxt().setEnabled(true);
            screen.getSubmitbtn().setEnabled(true);
        }
    }

    private void submitBtnAction() {
        try {
            // get new price
            double newPrice = Double.parseDouble(screen.getNewPricetxt().getText());
            // set price of Map
            DBaseStock.setPrice(screen.getSymbolTxt().getText(), newPrice);
            Offer change = new Offer("By Server", screen.getSymbolTxt().getText(), new Date(), newPrice); // add new
                                                                                                          // change as
                                                                                                          // an offer
            OfferDB.addOffer(change); // add the new change to offer list
            screen.dispose();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Price entered is invalid"); // if error value given as input
        }
    }

}
