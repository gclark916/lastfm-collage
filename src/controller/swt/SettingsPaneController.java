package controller.swt;

import java.util.Map;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;

import gui.swt.SettingsPane;
import base.CollageSettings;
import base.TimePeriod;

public class SettingsPaneController {

	private CollageSettings settings;
	private SettingsPane settingsPane;

	public SettingsPaneController(CollageSettings initialSettings, SettingsPane settingsPane) {
		this.settings = initialSettings;
		this.setSettingsPane(settingsPane);
		
		settingsPane.getUsernameTextBox().setText(this.settings.username);
		settingsPane.getUsernameTextBox().getUsernameText().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				settings.username = ((Text) e.widget).getText();
			}
		});
		
		Map<TimePeriod, Button> buttonMap = settingsPane.getTimePeriodButtonGroup().getTimePeriodButtonMap();
		Button button = buttonMap.get(this.settings.period);
		button.setSelection(true);
		for (TimePeriod period : TimePeriod.values()) {
			button = buttonMap.get(period);
			button.setData(period);
			button.addSelectionListener(new SelectionAdapter() {
				public void widgetSelected(SelectionEvent e) {
					TimePeriod period = (TimePeriod) ((Button) e.getSource()).getData();
					settings.period = period;
				}
			});
		}
		
		settingsPane.getDimensionsGroup().getRowSpinner().setValues(this.settings.rowCount, 1, 100, 0, 1, 5);
		settingsPane.getDimensionsGroup().getRowSpinner().getSpinner().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				settings.rowCount = ((Spinner) e.getSource()).getSelection();
			}
		});
		
		settingsPane.getDimensionsGroup().getColSpinner().setValues(this.settings.rowCount, 1, 100, 0, 1, 5);
		settingsPane.getDimensionsGroup().getColSpinner().getSpinner().addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				settings.colCount = ((Spinner) e.getSource()).getSelection();
			}
		});
		
		settingsPane.getShowNamesCheckbox().setSelection(this.settings.drawText);
		settingsPane.getShowNamesCheckbox().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				settings.drawText = ((Button) e.getSource()).getSelection();
			}
		});
	}

	public SettingsPane getSettingsPane() {
		return settingsPane;
	}

	public void setSettingsPane(SettingsPane settingsPane) {
		this.settingsPane = settingsPane;
	}

}
