
package ch.xwr.seicentobilling.ui.desktop;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.Resource;
import com.vaadin.server.StreamResource;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;
import com.xdev.res.ApplicationResource;
import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevHorizontalLayout;
import com.xdev.ui.entitycomponent.table.XdevTable;

import ch.xwr.seicentobilling.entities.RowImage;

public class FunctionUpDownloadRowFile extends XdevHorizontalLayout {

	public static class Generator implements ColumnGenerator {
		@Override
		public Object generateCell(final Table table, final Object itemId, final Object columnId) {

			return new FunctionUpDownloadRowFile(table, itemId, columnId);
		}
	}

	private final Table customizedTable;
	private final Object itemId;
	private final Object columnId;

	private FunctionUpDownloadRowFile(final Table customizedTable, final Object itemId, final Object columnId) {
		super();

		this.customizedTable = customizedTable;
		this.itemId = itemId;
		this.columnId = columnId;

		this.initUI();

		//Downloader init
		final Resource res = getInputStream();
		final FileDownloader fd = new FileDownloader(res);
		fd.extend(this.btnDownload);
	}

	public Table getTable() {
		return this.customizedTable;
	}

	public Object getItemId() {
		return this.itemId;
	}

	public Object getColumnId() {
		return this.columnId;
	}

	@SuppressWarnings("unchecked")
	public RowImage getBean() {
		return ((XdevTable<RowImage>) getTable()).getBeanContainerDataSource().getItem(getItemId()).getBean();
	}

	/**
	 * Event handler delegate method for the {@link XdevButton} {@link #btnDownload}.
	 *
	 * @see ClickListener#buttonClick(ClickEvent)
	 * @eventHandlerDelegate
	 */
	private void btnDownload_buttonClick(final ClickEvent event) {
		selectItem();

		//File temp = writeToTempFile(getBean());
		startDownload();
	}


	private void startDownload() {
//		final Resource res = getInputStream();
//
//		final FileDownloader fd = new FileDownloader(res);
//		fd.extend(this.btnDownload);

		Notification.show("Download gestartet für: " + getBean().getRimName(), Notification.Type.TRAY_NOTIFICATION);
	}

	//get Stream directly from Buffer
	private Resource getInputStream() {
        final StreamResource.StreamSource source = new StreamResource.StreamSource() {
            @Override
            public InputStream getStream() {
                final ByteArrayInputStream inStream = new ByteArrayInputStream(getBean().getRimImage());
                return inStream;
            }
        };

		final Resource res = new StreamResource(source, getBean().getRimName());

		return res;
	}

//	private File writeToTempFile(RowImage rec) {
//		File temp = null;
//
//		FileOutputStream out = null;
//		try {
//			temp = File.createTempFile(rec.getRimName(), ".tmp");
//			out = new FileOutputStream(temp);
//			out.write(rec.getRimFile());
//
//		} catch (Exception e){
//			e.printStackTrace();
//		} finally {
//			if (out != null) {
//				try {
//					out.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return temp;
//	}

//	   public void createExportContent()
//	   {
//	       try
//	       {
//	           final File file = File.createTempFile("export", getFileExtension());
//	           file.deleteOnExit();
//
//	           BufferedOutputStream bufferedStream = new BufferedOutputStream(new FileOutputStream(file));
//	           generateContent(bufferedStream);
//	           bufferedStream.close();
//
//	           fileDownloader.setFileDownloadResource(FileDownloadUtils.createFileResource(file));
//	        }
//	        catch (Exception e)
//	        {
//	            throw new RuntimeException("Error exporting!", e);
//	        }
//	    }

	/**
	 * Event handler delegate method for the {@link XdevHorizontalLayout}.
	 *
	 * @see LayoutClickListener#layoutClick(LayoutClickEvent)
	 * @eventHandlerDelegate
	 */
	private void this_layoutClick(final LayoutClickEvent event) {
		selectItem();
	}

	private void selectItem() {
		getTable().select(getItemId());
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.btnDownload = new XdevButton();

		this.setSpacing(false);
		this.setMargin(new MarginInfo(false));
		this.btnDownload
				.setIcon(new ApplicationResource(this.getClass(), "WebContent/WEB-INF/resources/images/Download1.png"));
		this.btnDownload.setCaption("Download");

		this.btnDownload.setSizeUndefined();
		this.addComponent(this.btnDownload);
		this.setComponentAlignment(this.btnDownload, Alignment.MIDDLE_CENTER);
		this.setSizeUndefined();

		this.addLayoutClickListener(event -> this.this_layoutClick(event));
		this.btnDownload.addClickListener(event -> this.btnDownload_buttonClick(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevButton btnDownload; // </generated-code>


}