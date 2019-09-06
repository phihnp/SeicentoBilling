package ch.xwr.seicentobilling.ui.desktop;

import java.io.File;

import com.vaadin.event.ShortcutAction;
import com.vaadin.external.org.slf4j.Logger;
import com.vaadin.external.org.slf4j.LoggerFactory;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Window;
import com.xdev.res.ApplicationResource;
import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevGridLayout;
import com.xdev.ui.XdevImage;
import com.xdev.ui.XdevLabel;
import com.xdev.ui.XdevTextField;
import com.xdev.ui.XdevUpload;
import com.xdev.ui.XdevView;

import ch.xwr.seicentobilling.business.ExcelHandler;
import ch.xwr.seicentobilling.business.UploadReceiverExcel;
import ch.xwr.seicentobilling.entities.Periode;

public class ExcelUploadPopup extends XdevView {
	/** Logger initialized */
	private static final Logger LOG = LoggerFactory.getLogger(ExcelUploadPopup.class);

	private final Periode periodeBean;

	/**
	 *
	 */
	public ExcelUploadPopup() {
		super();
		this.initUI();

		// get Parameter
		this.periodeBean = (Periode) UI.getCurrent().getSession().getAttribute("periodebean");

		setDefaultValue();
		setupUploader();

		this.labelStatus.setValue("");
	}


	private void setDefaultValue() {
		int isheet = this.periodeBean.getPerMonth().getValue();

		if (isheet == 12) {
			isheet = 1;
		} else {
			isheet = isheet+1;
		}
		this.textFieldSheet.setValue("" + isheet);

	}


	public static Window getPopupWindow() {
		final Window win = new Window();

		win.setWidth("600");
		win.setHeight("260");
		win.center();
		win.setModal(true);
		win.setContent(new ExcelUploadPopup());

		return win;
	}

	private void setupUploader() {
		//uploader
		final UploadReceiverExcel rec = new UploadReceiverExcel();
		this.upload.setReceiver(rec);

        this.upload.addSucceededListener(new Upload.SucceededListener() {
            @Override
			public void uploadSucceeded(final SucceededEvent event) {
                // This method gets called when the upload finished successfully
        	    //System.out.println("________________ UPLOAD SUCCEEDED y");
        	    rec.uploadSucceeded(event);
        		Notification.show("Datei erfolgreich hochgeladen", Type.TRAY_NOTIFICATION);
        		LOG.info("Excel Datei hochgeladen " + event.getFilename());
        	    processUploadedFile(rec.getOutFile());
            }

        });
	}

	private void processUploadedFile(final File outFile) {
		try {
			final int sheet = new Integer(this.textFieldSheet.getValue()).intValue();
			final ExcelHandler exc = new ExcelHandler();
			exc.importReportLine(outFile, sheet, this.periodeBean);
			Notification.show("Excel Datei importiert", Type.TRAY_NOTIFICATION);
			LOG.info("Excel Datei erfolgreich importiert");

			this.upload.setEnabled(false);
			this.labelStatus.setValue("Daten erfolgreich importiert. Bitte Rapport überprüfen.");

		} catch (final Exception e) {
			this.labelStatus.setValue("Importieren ist fehlgeschlagen!");
			Notification.show("Fehler beim Importieren", e.getMessage(), Notification.Type.ERROR_MESSAGE);
			LOG.error(e.getLocalizedMessage());
		} finally {
			//cleanup
			outFile.delete();
		}

	}

	/**
	 * Event handler delegate method for the {@link XdevButton} {@link #cmdClose}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmdClose_buttonClick(final Button.ClickEvent event) {
		((Window) this.getParent()).close();
	}


	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.gridLayout = new XdevGridLayout();
		this.image = new XdevImage();
		this.label = new XdevLabel();
		this.label2 = new XdevLabel();
		this.textFieldSheet = new XdevTextField();
		this.label3 = new XdevLabel();
		this.textFieldRow = new XdevTextField();
		this.upload = new XdevUpload();
		this.cmdClose = new XdevButton();
		this.labelStatus = new XdevLabel();

		this.gridLayout.setMargin(new MarginInfo(false, true, true, true));
		this.image.setSource(new ApplicationResource(this.getClass(), "WebContent/WEB-INF/resources/images/excel24.png"));
		this.label.setStyleName("h3");
		this.label.setValue("Excel Datei hochladen");
		this.label2.setDescription("Die Zahl entspricht dem Monat (z.B. 9 = September)");
		this.label2.setValue("Arbeitsblatt");
		this.textFieldSheet.setDescription("Welches Arbeitsblatt in Excel beginnend mit 0");
		this.textFieldSheet.setValue("2");
		this.label3.setDescription("Standardwert 15. Daten bis und mit dieser Zeile in Excel werden ignoriert.");
		this.label3.setValue("Offset Zeile");
		this.textFieldRow.setDescription("Startzeile der Rapporte beginnend bei 0");
		this.textFieldRow.setEnabled(false);
		this.textFieldRow.setValue("15");
		this.upload.setButtonCaption("Start Prozess");
		this.cmdClose.setIcon(FontAwesome.CLOSE);
		this.cmdClose.setCaption("Schliessen");
		this.cmdClose.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);
		this.labelStatus.setValue("Label");

		this.gridLayout.setColumns(3);
		this.gridLayout.setRows(6);
		this.image.setSizeUndefined();
		this.gridLayout.addComponent(this.image, 0, 0);
		this.gridLayout.setComponentAlignment(this.image, Alignment.MIDDLE_LEFT);
		this.label.setSizeUndefined();
		this.gridLayout.addComponent(this.label, 1, 0);
		this.label2.setSizeUndefined();
		this.gridLayout.addComponent(this.label2, 0, 1);
		this.textFieldSheet.setSizeUndefined();
		this.gridLayout.addComponent(this.textFieldSheet, 1, 1);
		this.label3.setSizeUndefined();
		this.gridLayout.addComponent(this.label3, 0, 2);
		this.textFieldRow.setSizeUndefined();
		this.gridLayout.addComponent(this.textFieldRow, 1, 2);
		this.upload.setWidth(-1, Unit.PIXELS);
		this.upload.setHeight(100, Unit.PERCENTAGE);
		this.gridLayout.addComponent(this.upload, 0, 3, 1, 3);
		this.cmdClose.setSizeUndefined();
		this.gridLayout.addComponent(this.cmdClose, 2, 3);
		this.labelStatus.setSizeUndefined();
		this.gridLayout.addComponent(this.labelStatus, 1, 4);
		this.gridLayout.setColumnExpandRatio(1, 20.0F);
		final CustomComponent gridLayout_vSpacer = new CustomComponent();
		gridLayout_vSpacer.setSizeFull();
		this.gridLayout.addComponent(gridLayout_vSpacer, 0, 5, 2, 5);
		this.gridLayout.setRowExpandRatio(5, 1.0F);
		this.gridLayout.setSizeFull();
		this.setContent(this.gridLayout);
		this.setSizeFull();

		this.cmdClose.addClickListener(event -> this.cmdClose_buttonClick(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevLabel label, label2, label3, labelStatus;
	private XdevButton cmdClose;
	private XdevUpload upload;
	private XdevImage image;
	private XdevGridLayout gridLayout;
	private XdevTextField textFieldSheet, textFieldRow;
	// </generated-code>

}
