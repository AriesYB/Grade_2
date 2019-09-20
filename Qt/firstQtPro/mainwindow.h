#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include "result.h"
#include <list>
#include "queen.h"

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    bool isOK();
    void calculate(int n);
    ~MainWindow();
private slots:
    void showResult();
    void backToMain();
private:
    Ui::MainWindow *ui;
    Result result;
    std::list<int *> results;//解集
    Queen queen;
};

#endif // MAINWINDOW_H
