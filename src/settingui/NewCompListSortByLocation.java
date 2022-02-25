package settingui;

import users.User;

public interface NewCompListSortByLocation<T> {
    T getNewComponentList(T oldJPanelList, User currentUser);
}

