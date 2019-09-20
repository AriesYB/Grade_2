/********************************************************************************
** Form generated from reading UI file 'result.ui'
**
** Created by: Qt User Interface Compiler version 5.10.0
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_RESULT_H
#define UI_RESULT_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QTableView>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_Result
{
public:
    QPushButton *back;
    QTableView *tableView;
    QLabel *label;

    void setupUi(QWidget *Result)
    {
        if (Result->objectName().isEmpty())
            Result->setObjectName(QStringLiteral("Result"));
        Result->resize(800, 600);
        back = new QPushButton(Result);
        back->setObjectName(QStringLiteral("back"));
        back->setGeometry(QRect(640, 520, 101, 41));
        tableView = new QTableView(Result);
        tableView->setObjectName(QStringLiteral("tableView"));
        tableView->setGeometry(QRect(60, 50, 681, 441));
        label = new QLabel(Result);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(220, 535, 31, 21));

        retranslateUi(Result);

        QMetaObject::connectSlotsByName(Result);
    } // setupUi

    void retranslateUi(QWidget *Result)
    {
        Result->setWindowTitle(QApplication::translate("Result", "\350\256\241\347\256\227\347\273\223\346\236\234", nullptr));
        back->setText(QApplication::translate("Result", "\350\277\224\345\233\236", nullptr));
        label->setText(QString());
    } // retranslateUi

};

namespace Ui {
    class Result: public Ui_Result {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_RESULT_H
