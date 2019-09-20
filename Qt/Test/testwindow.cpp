#include "testwindow.h"
#include "ui_testwindow.h"
#include <iostream>

TestWindow::TestWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::TestWindow)
{
    ui->setupUi(this);
    connect(ui->pushButton,&QPushButton::clicked,this,&TestWindow::deal);

}

TestWindow::~TestWindow()
{
    delete ui;
}

void TestWindow::deal()
{
    std::cout<<"aaa";
}
