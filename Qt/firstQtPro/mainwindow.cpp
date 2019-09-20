#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    connect(ui->pushButton,&QPushButton::clicked,this,&MainWindow::showResult);
    connect(&result,&Result::backSignal,this,&MainWindow::backToMain);
    resize(800,600);
}

void MainWindow::calculate(int n)
{
    queen.calculateIt(n);
    result.setNum(n);
    results = queen.getResult();
}

bool MainWindow::isOK()
{
    bool ok;
    QString str = ui->textEdit->toPlainText();
    int num=str.toInt(&ok,10);
    if(ok>=1)//如果成功是整数
    {
     calculate(num);
    }
    return ok;
}

void MainWindow::showResult() //显示结果
{
    if(isOK())
    {
        result.showData(results);
        result.show();
        this->hide();
        ui->textEdit->clear();
    }
}

void MainWindow::backToMain()
{
    result.hide();
    result.clearPoint();
    this->show();
}

MainWindow::~MainWindow()
{
    delete ui;
}
