package ch.xwr.seicentobilling.ui.phone;

import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClientConnector;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.xdev.ui.XdevVerticalLayout;
import com.xdev.ui.XdevView;
import com.xdev.ui.entitycomponent.XdevBeanContainer;
import com.xdev.ui.entitycomponent.table.XdevTable;
import com.xdev.ui.navigation.Navigation;
import com.xdev.ui.navigation.NavigationParameter;

import ch.xwr.seicentobilling.business.Seicento;
import ch.xwr.seicentobilling.dal.CostAccountDAO;
import ch.xwr.seicentobilling.dal.PeriodeDAO;
import ch.xwr.seicentobilling.entities.CostAccount;
import ch.xwr.seicentobilling.entities.Periode;
import ch.xwr.seicentobilling.entities.Periode_;

public class PeriodeView extends XdevView {

	@NavigationParameter
	private String target;
	@NavigationParameter
	private String string;
	/**
	 *
	 */
	public PeriodeView() {
		super();
		this.initUI();
	}

	@Override
	public void enter(final ViewChangeListener.ViewChangeEvent event) {
		super.enter(event);

		this.target = Navigation.getParameter(event, "string", String.class);

		this.string = Navigation.getParameter(event, "string", String.class);

	}

	/**
	 * Event handler delegate method for the {@link XdevTable} {@link #table}.
	 *
	 * @see ItemClickEvent.ItemClickListener#itemClick(ItemClickEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void table_itemClick(final ItemClickEvent event) {
		final Periode bean = (Periode) event.getItemId();

		if ("Expense".equals(this.target)) {
			Navigation.to("expenseListView").parameter("periode", bean).navigate();
		} else {
			Navigation.to("projectLineListView").parameter("periode", bean).navigate();
		}

	}

	/**
	 * Event handler delegate method for the {@link XdevVerticalLayout}
	 * {@link #verticalLayout}.
	 *
	 * @see ClientConnector.AttachListener#attach(ClientConnector.AttachEvent)
	 * @eventHandlerDelegate Do NOT delete, used by UI designer!
	 */
	private void verticalLayout_attach(final ClientConnector.AttachEvent event) {
		loadTable();

	}

	private void loadTable() {
		final CostAccountDAO dao = new CostAccountDAO();
		final CostAccount bean;
		final String name = Seicento.getUserName();

		if ("unknown".equals(name) || name.isEmpty()){
			bean = dao.find((long) 1); //Dummy
		} else {
			bean = dao.findByName(Seicento.getUserName()).get(0);
		}

		final XdevBeanContainer<Periode> myList = this.table.getBeanContainerDataSource();
		myList.removeAll();
		myList.addAll(new PeriodeDAO().findByCostAccountOpenPeriode(bean));

		this.table.refreshRowCache();
		this.table.getBeanContainerDataSource().refresh();

	}

	/*
	 * WARNING: Do NOT edit!<br>The content of this method is always regenerated by
	 * the UI designer.
	 */
	// <generated-code name="initUI">
	private void initUI() {
		this.verticalLayout = new XdevVerticalLayout();
		this.table = new XdevTable<>();

		this.setStyleName("large");
		this.verticalLayout.setStyleName("large");
		this.verticalLayout.setSpacing(false);
		this.verticalLayout.setMargin(new MarginInfo(false));
		this.table.setStyleName("large");
		this.table.setContainerDataSource(Periode.class, false);
		this.table.setVisibleColumns(Periode_.perName.getName());
		this.table.setColumnHeader("perName", "offene Perioden");

		this.table.setSizeFull();
		this.verticalLayout.addComponent(this.table);
		this.verticalLayout.setComponentAlignment(this.table, Alignment.MIDDLE_CENTER);
		this.verticalLayout.setExpandRatio(this.table, 100.0F);
		this.verticalLayout.setSizeFull();
		this.setContent(this.verticalLayout);
		this.setSizeFull();

		this.verticalLayout.addAttachListener(event -> this.verticalLayout_attach(event));
		this.table.addItemClickListener(event -> this.table_itemClick(event));
	} // </generated-code>

	// <generated-code name="variables">
	private XdevVerticalLayout verticalLayout;
	private XdevTable<Periode> table;
	// </generated-code>

}
