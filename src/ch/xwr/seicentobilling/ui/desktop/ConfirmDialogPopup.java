package ch.xwr.seicentobilling.ui.desktop;

import com.vaadin.event.ShortcutAction;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import com.xdev.res.ApplicationResource;
import com.xdev.ui.XdevButton;
import com.xdev.ui.XdevHorizontalLayout;
import com.xdev.ui.XdevImage;
import com.xdev.ui.XdevLabel;
import com.xdev.ui.XdevPanel;
import com.xdev.ui.XdevVerticalLayout;
import com.xdev.ui.XdevView;

public class ConfirmDialogPopup extends XdevView {

	/**
	 *
	 */
	public ConfirmDialogPopup() {
		super();
		this.initUI();
	}

	/**
	 *
	 */
	public ConfirmDialogPopup(final String title, final String text, final int type) {
		super();
		this.initUI();

		this.setCaption(title);
		this.panel.setCaption(title);
		this.label.setValue(text);
	}

	/**
	 * Event handler delegate method for the {@link XdevButton} {@link #cmdOk}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmdOk_buttonClick(final Button.ClickEvent event) {
		UI.getCurrent().getSession().setAttribute(String.class, "cmdOk");

		((Window) this.getParent()).close();
	}

	/**
	 * Event handler delegate method for the {@link XdevButton} {@link #cmdCancel}.
	 *
	 * @see Button.ClickListener#buttonClick(Button.ClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void cmdCancel_buttonClick(final Button.ClickEvent event) {
		UI.getCurrent().getSession().setAttribute(String.class, "cmdCancel");
		((Window) this.getParent()).close();
	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.panel = new XdevPanel();
		this.verticalLayout = new XdevVerticalLayout();
		this.horizontalLayout = new XdevHorizontalLayout();
		this.image = new XdevImage();
		this.label = new XdevLabel();
		this.horizontalLayout2 = new XdevHorizontalLayout();
		this.cmdOk = new XdevButton();
		this.cmdCancel = new XdevButton();

		this.panel.setCaption("Panel");
		this.panel.setStyleName("bar closable dark");
		this.verticalLayout.setSpacing(false);
		this.horizontalLayout.setMargin(new MarginInfo(false, true, false, true));
		this.image
				.setIcon(new ApplicationResource(this.getClass(), "WebContent/WEB-INF/resources/images/questionmark2.jpg"));
		this.label.setStyleName("bold colored h2");
		this.label.setValue("Label");
		this.horizontalLayout2.setMargin(new MarginInfo(false, true, false, true));
		this.cmdOk.setCaption("Ok");
		this.cmdOk.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		this.cmdCancel.setCaption("Abbrechen");
		this.cmdCancel.setClickShortcut(ShortcutAction.KeyCode.ESCAPE);

		this.image.setSizeUndefined();
		this.horizontalLayout.addComponent(this.image);
		this.horizontalLayout.setComponentAlignment(this.image, Alignment.MIDDLE_LEFT);
		this.label.setSizeUndefined();
		this.horizontalLayout.addComponent(this.label);
		this.horizontalLayout.setComponentAlignment(this.label, Alignment.TOP_CENTER);
		final CustomComponent horizontalLayout_spacer = new CustomComponent();
		horizontalLayout_spacer.setSizeFull();
		this.horizontalLayout.addComponent(horizontalLayout_spacer);
		this.horizontalLayout.setExpandRatio(horizontalLayout_spacer, 1.0F);
		this.cmdOk.setSizeUndefined();
		this.horizontalLayout2.addComponent(this.cmdOk);
		this.horizontalLayout2.setComponentAlignment(this.cmdOk, Alignment.MIDDLE_CENTER);
		this.cmdCancel.setSizeUndefined();
		this.horizontalLayout2.addComponent(this.cmdCancel);
		this.horizontalLayout2.setComponentAlignment(this.cmdCancel, Alignment.MIDDLE_CENTER);
		final CustomComponent horizontalLayout2_spacer = new CustomComponent();
		horizontalLayout2_spacer.setSizeFull();
		this.horizontalLayout2.addComponent(horizontalLayout2_spacer);
		this.horizontalLayout2.setExpandRatio(horizontalLayout2_spacer, 1.0F);
		this.horizontalLayout.setWidth(100, Unit.PERCENTAGE);
		this.horizontalLayout.setHeight(-1, Unit.PIXELS);
		this.verticalLayout.addComponent(this.horizontalLayout);
		this.verticalLayout.setComponentAlignment(this.horizontalLayout, Alignment.MIDDLE_LEFT);
		this.horizontalLayout2.setWidth(100, Unit.PIXELS);
		this.horizontalLayout2.setHeight(-1, Unit.PIXELS);
		this.verticalLayout.addComponent(this.horizontalLayout2);
		this.verticalLayout.setComponentAlignment(this.horizontalLayout2, Alignment.MIDDLE_CENTER);
		final CustomComponent verticalLayout_spacer = new CustomComponent();
		verticalLayout_spacer.setSizeFull();
		this.verticalLayout.addComponent(verticalLayout_spacer);
		this.verticalLayout.setExpandRatio(verticalLayout_spacer, 1.0F);
		this.verticalLayout.setSizeFull();
		this.panel.setContent(this.verticalLayout);
		this.panel.setSizeFull();
		this.setContent(this.panel);
		this.setSizeFull();

		this.cmdOk.addClickListener(event -> this.cmdOk_buttonClick(event));
		this.cmdCancel.addClickListener(event -> this.cmdCancel_buttonClick(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevLabel label;
	private XdevButton cmdOk, cmdCancel;
	private XdevHorizontalLayout horizontalLayout, horizontalLayout2;
	private XdevImage image;
	private XdevPanel panel;
	private XdevVerticalLayout verticalLayout;
	// </generated-code>

}
