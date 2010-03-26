/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.preferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelProviderProxy;
import de.ovgu.cide.languages.LanguageExtensionManager;
import de.ovgu.cide.languages.LanguageExtensionProxy;

public class PluginPreferencePage extends PreferencePage implements
		IWorkbenchPreferencePage {

	private List<LangSelection> langEditors = new ArrayList<LangSelection>();
	private Combo featureModelEditor;
	// private Combo storageProviderEditor;
	private List<FeatureModelProviderProxy> featureModelProviders;
	// private List<StorageProviderProxy> storageProviders;
	private SelectionListener updateListener;

	public PluginPreferencePage() {
		// super(GRID);
		setPreferenceStore(CIDECorePlugin.getDefault().getPreferenceStore());
		setDescription("CIDE Languages:");
	}

	private class LangSelection {
		private Button checkbox;
		private LanguageExtensionProxy langExtension;

		LangSelection(LanguageExtensionProxy langExtension, Composite parent) {
			checkbox = new Button(parent, SWT.CHECK);
			checkbox.setText(formatLangExt(langExtension));
			checkbox.addSelectionListener(updateListener);
			this.langExtension = langExtension;
		}

		public boolean isSelected() {
			return checkbox.getSelection();
		}

		public String getPrefId() {
			return PreferenceConstants.P_LANGPREFIX + langExtension.getId();
		}

		public void setSelected(boolean isSelected) {
			checkbox.setSelection(isSelected);
		}
	}

	private void createLanguagesGroup(Composite parent) {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = false;
		gridData.verticalAlignment = GridData.CENTER;
		gridData.horizontalAlignment = GridData.BEGINNING;
		gridData.horizontalSpan = 2;
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		Group languagesGroup = new Group(parent, SWT.NONE);
		languagesGroup.setText("Supported Languages");
		languagesGroup.setLayoutData(gridData);
		languagesGroup.setLayout(gridLayout);

		// language extensions
		List<LanguageExtensionProxy> langExts = LanguageExtensionManager
				.getInstance().getAllLanguageExtensions();
		for (LanguageExtensionProxy langExt : langExts) {
			LangSelection langSelection = new LangSelection(langExt,
					languagesGroup);
			langEditors.add(langSelection);
		}
	}

	@Override
	protected Control createContents(Composite parent) {
		updateListener = new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent e) {
			}

			public void widgetSelected(SelectionEvent e) {
				update();
			}
		};

		GridLayout gridLayout1 = new GridLayout();
		gridLayout1.numColumns = 2;
		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(gridLayout1);
		createLanguagesGroup(parent);

		// feature model
		featureModelProviders = FeatureModelManager.getInstance()
				.getFeatureModelProviders();
		Label featureModelLabel = new Label(parent, SWT.NONE);
		featureModelLabel.setText("Feature Model Type");
		featureModelEditor = new Combo(parent, SWT.READ_ONLY);
		featureModelEditor.setItems(getFeatureModels(featureModelProviders));
		featureModelEditor.addSelectionListener(updateListener);

		createRestartWarningLabel(parent);

		// // storage providers
		// storageProviders = StorageProviderManager.getInstance()
		// .getStorageProviders();
		// Label storageProviderLabel = new Label(parent, SWT.NONE);
		// storageProviderLabel.setText("Storage Provider");
		// storageProviderEditor = new Combo(parent, SWT.READ_ONLY);
		// storageProviderEditor.setItems(getStorageProviders(storageProviders));
		// storageProviderEditor.addSelectionListener(updateListener);
		load();
		validate();
		return parent;
	}

	private void createRestartWarningLabel(Composite parent) {
		GridData gridData = new GridData();
		gridData.horizontalSpan = 2;
		Label languagesGroup = new Label(parent, SWT.NONE);
		languagesGroup
				.setText("Changing the Feature Model Provider requires a restart of Eclipse.");
		languagesGroup.setLayoutData(gridData);
	}

	private void load() {
		IPreferenceStore pref = getPreferenceStore();
		for (LangSelection lang : langEditors) {
			lang.setSelected(pref.getBoolean(lang.getPrefId()));
		}

		selectFeatureModel(pref
				.getString(PreferenceConstants.P_FEATUREMODELPROVIDER));
		validate();
	}

	private void store() {
		IPreferenceStore pref = getPreferenceStore();
		for (LangSelection lang : langEditors) {
			pref.setValue(lang.getPrefId(), lang.isSelected());
		}
		int idx = featureModelEditor.getSelectionIndex();
		if (idx == -1)
			pref.setToDefault(PreferenceConstants.P_FEATUREMODELPROVIDER);
		else
			pref.setValue(PreferenceConstants.P_FEATUREMODELPROVIDER,
					featureModelProviders.get(idx).getId());
	}

	@Override
	protected void performDefaults() {
		super.performDefaults();
		loadDefault();
	}

	@Override
	public boolean performOk() {
		store();
		return super.performOk();
	}

	private void loadDefault() {
		IPreferenceStore pref = getPreferenceStore();
		for (LangSelection lang : langEditors) {
			lang.setSelected(pref.getDefaultBoolean(lang.getPrefId()));
		}

		selectFeatureModel(pref
				.getDefaultString(PreferenceConstants.P_FEATUREMODELPROVIDER));
		validate();
	}

	private void selectFeatureModel(String featureModelProviderId) {
		int featureModelProviderIdx = -1;
		for (int i = 0; i < featureModelProviders.size(); i++) {
			FeatureModelProviderProxy featureModelProvider = featureModelProviders
					.get(i);
			if (featureModelProvider.getId().equals(featureModelProviderId))
				featureModelProviderIdx = i;
		}
		featureModelEditor.select(featureModelProviderIdx);
	}

	protected void update() {
		validate();
	}

	private boolean validate() {
		boolean langOverlappingError = false;
		String overlappingFileExtension = "";
		Set<String> supportedFileExtensions = new HashSet<String>();
		for (LangSelection langEditor : langEditors)
			if (!langOverlappingError && langEditor.isSelected()) {
				for (String fileExtension : langEditor.langExtension
						.getFileExtensions()) {
					if (supportedFileExtensions.contains(fileExtension)) {
						overlappingFileExtension = fileExtension;
						langOverlappingError = true;
					} else
						supportedFileExtensions.add(fileExtension);
				}
			}
		if (langOverlappingError) {
			return error("Multiple Language Extensions provide facilities for the same language extension "
					+ overlappingFileExtension);

		}

		if (featureModelEditor.getSelectionIndex() == -1)
			return error("No feature model provider selected.");

		return error(null);

	}

	private boolean error(String string) {
		setErrorMessage(string);
		setValid(string == null);
		return string == null;
	}

	private String[] getFeatureModels(
			List<FeatureModelProviderProxy> featureModelProviders) {
		String[] result = new String[featureModelProviders.size()];
		int idx = 0;
		for (FeatureModelProviderProxy fmp : featureModelProviders) {
			result[idx] = fmp.getName();
			idx++;
		}
		return result;
	}

	// private String[] getStorageProviders(
	// List<StorageProviderProxy> storageProviders) {
	//
	// String[] result = new String[storageProviders.size()];
	// int idx = 0;
	// for (StorageProviderProxy storageProvider : storageProviders) {
	// result[idx] = storageProvider.getName();
	// idx++;
	// }
	// return result;
	// }

	private String formatLangExt(LanguageExtensionProxy langExt) {
		return langExt.getName() + " (" + langExt.printFileExtensions(" ,")
				+ ")";
	}

	// @Override
	// protected void checkState() {
	// super.checkState();
	// if (!super.isValid())
	// return;
	//
	// boolean langOverlappingError = false;
	// String overlappingFileExtension = "";
	// Set<String> supportedFileExtensions = new HashSet<String>();
	// for (BooleanFieldEditor langEditor : langEditors)
	// if (!langOverlappingError && langEditor.getBooleanValue()) {
	// LanguageExtensionProxy lang = LanguageExtensionManager
	// .getInstance().getLanguageExtension(
	// langEditor.getPreferenceName());
	// for (String fileExtension : lang.getFileExtensions()) {
	// if (supportedFileExtensions.contains(fileExtension)) {
	// overlappingFileExtension = fileExtension;
	// langOverlappingError = true;
	// } else
	// supportedFileExtensions.add(fileExtension);
	// }
	// }
	// if (langOverlappingError) {
	// setErrorMessage("Multiple Language Extensions provide facilities for the same language extension "
	// + overlappingFileExtension);
	// setValid(false);
	// } else {
	// setErrorMessage(null);
	// setValid(true);
	// }
	//		
	// featureModelEditor.get()
	// private ComboFieldEditor featureModelEditor;
	// private ComboFieldEditor storageProviderEditor;
	//		
	// }
	//
	// @Override
	// public void propertyChange(PropertyChangeEvent event) {
	// super.propertyChange(event);
	// if (event.getProperty().equals(FieldEditor.VALUE))
	// if (langEditors.contains(event.getSource()))
	// checkState();
	// }

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init(IWorkbench workbench) {
	}

}