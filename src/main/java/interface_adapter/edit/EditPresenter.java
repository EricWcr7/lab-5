package interface_adapter.edit;

import interface_adapter.ViewManagerModel;
import use_case.edit.EditOutputBoundary;

public class EditPresenter implements EditOutputBoundary {
    private final EditViewModel editViewModel;
    private final ViewManagerModel viewManagerModel;

    public EditPresenter(ViewManagerModel viewManagerModel, EditViewModel editViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.editViewModel = editViewModel;
    }


    @Override
    public void showEditView() {

    }
}
