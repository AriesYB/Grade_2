#ifndef TESTWINDOW_H
#define TESTWINDOW_H

#include <QMainWindow>
#include <list>

namespace Ui {
class TestWindow;
}

class TestWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit TestWindow(QWidget *parent = 0);
    ~TestWindow();
    void deal();
private:
    Ui::TestWindow *ui;
    std::list<int> l;
};

#endif // TESTWINDOW_H
