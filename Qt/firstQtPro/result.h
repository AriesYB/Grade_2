#ifndef RESULT_H
#define RESULT_H

#include <QWidget>
#include <list>
#include <QStandardItemModel>
#include <QTableView>
#include <QHeaderView>

namespace Ui {
class Result;
}

class Result : public QWidget
{
    Q_OBJECT
public:
    explicit Result(QWidget *parent = 0);
    void showData(std::list<int *> l);
    void clearPoint();
    void setNum(int n);
    ~Result();
signals:
    void backSignal();
private slots:
    void backToMainSlot();
private:
    Ui::Result *ui;
    QTableView *table;
    QStandardItemModel *model;
    int num;
private slots:
//    int pageCount(QTableView *p);
//    bool pageTo(QTableView *p, int pageNO);
//    bool pageUp(QTableView *p,bool isLoop);
//    bool pageDown(QTableView *p,bool isLoop);
//    bool pageHome(QTableView *p);
//    bool pageEnd(QTableView *p);
};

#endif // RESULT_H
