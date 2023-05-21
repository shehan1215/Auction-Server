import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class ServerController extends Thread {
    private static ServerScreen gui;

    public ServerController() {
        gui = new ServerScreen();
        gui.setVisible(true);
        gui.pack();
        gui.setLocationRelativeTo(null);
        gui.setResizable(false);
        gui.getChangePriceBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePriceBtnAction();
            }
        });
        gui.getListStockBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StocklistBtnAction();
            }
        });

    }

    private void changePriceBtnAction() {
        PriceChangeController con = new PriceChangeController();
    }

    private void StocklistBtnAction() {
        StockListController con = new StockListController();
    }

    private void loop() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            }
            gui.getFb().setText("" + DBaseStock.getStockItem("FB").getPrice());
            gui.getVrtu().setText("" + DBaseStock.getStockItem("VRTU").getPrice());
            gui.getMsft().setText("" + DBaseStock.getStockItem("MSFT").getPrice());
            gui.getGoogl().setText("" + DBaseStock.getStockItem("GOOGL").getPrice());
            gui.getYhoo().setText("" + DBaseStock.getStockItem("YHOO").getPrice());
            gui.getXlnx().setText("" + DBaseStock.getStockItem("XLNX").getPrice());
            gui.getTsla().setText("" + DBaseStock.getStockItem("TSLA").getPrice());
            gui.getTxn().setText("" + DBaseStock.getStockItem("TXN").getPrice());
            // gui.getOffertable().
        }
    }

    public static void addDetailsToTable(Offer offerToAdd) {
        DefaultTableModel dtm = (DefaultTableModel) gui.getOffertable().getModel();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH-mm-ss");
        String date = dateFormat.format(offerToAdd.getDate());
        String time = timeFormat.format(offerToAdd.getDate());
        Object newRow[] = { offerToAdd.getClientName(), offerToAdd.getSymbol(), date, time, offerToAdd.getPrice() };
        dtm.addRow(newRow);
    }

    @Override
    public void run() {
        loop();
    }

}
