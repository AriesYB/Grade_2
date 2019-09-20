#include "result.h"
#include "ui_result.h"


Result::Result(QWidget *parent) :
    QWidget(parent),
    ui(new Ui::Result)
{
    ui->setupUi(this);
    table = ui->tableView;
    table->verticalHeader()->setDefaultSectionSize(60);
    table->horizontalHeader()->setDefaultSectionSize(60);
//    table->horizontalHeader()->setVisible(false);// 水平不可见
//    table->verticalHeader()->setVisible(false);// 垂直不可见
    connect(ui->back,&QPushButton::clicked,this,&Result::backToMainSlot);
    resize(800,600);
}

void Result::showData(std::list<int *> l)
{
//    qDebug()<<"一共有:\n"<<l.size()<<"种结果";
    model = new QStandardItemModel;
    table->setModel(model);
    model->setHeaderData(0,Qt::Horizontal,"行数");
    model->setRowCount(l.size());
    model->setColumnCount(num);
    std::list<int *>::iterator iter;
    int row = 0;
    for(iter = l.begin(); iter != l.end(); iter++)
    {
        int *arr = *iter;
        QStandardItem *item;
        for(int i =0;i<num;i++)
        {
            for(int j=0;j<num;j++)
            {
//                item = new QStandardItem(QString::number(arr[i*num+j]));
                if(arr[i*num+j]==1)
                {
//                    item->setIcon(QIcon("queen.png"));
                    item = new QStandardItem(QString::number(arr[i*num+j]));
                     model->setItem(row,j,item);
                }
            }
            row++;
        }

    }
}

void Result::backToMainSlot()
{
    emit backSignal();
}

void Result::clearPoint()
{
    delete model;
    model = NULL;
}

void Result::setNum(int n)
{
    this->num = n;
}

Result::~Result()
{
    delete ui;
}

//int Result::pageCount(QTableView *p)//QTableView 总页数
//{
//    if(p == NULL)  return -1;
//    int rowCount = p->model()->rowCount();
//    int rowHeight = p->rowHeight(0);
//    int tableViewHeight = p->height();
//    int rowCountPerPage = tableViewHeight/rowHeight-1;//每页显示行数
//    int ret = rowCount/rowCountPerPage;
//    int tem = rowCount%rowCountPerPage;
//    if (tem != 0) ret++;
//    return ret;
//}
//bool Result::pageTo(QTableView *p, int pageNO)//翻到指定页
//{
//    if(p == NULL)  return false;
//    int maxPage = pageCount(p);
//    if(pageNO > maxPage)  return false;
//    int rowCount = p->model()->rowCount();
//    int rowHeight = p->rowHeight(0);
//    int tableViewHeight = p->height();
//    int rowCountPerPage = tableViewHeight/rowHeight-1;		//每页显示行数
//    int canNotViewCount = rowCount-rowCountPerPage;			//看不见的行数
//    if(canNotViewCount == 0) return false;
//    int maxValue = p->verticalScrollBar()->maximumViewportSize();	// 当前SCROLLER最大显示值
//    if(maxValue == 0) return false;
//    int pageValue = (maxValue*rowCountPerPage)/canNotViewCount;
//    p->verticalScrollBar()->setSliderPosition(pageValue*(pageNO-1));
//}
//bool Result::pageUp(QTableView *p,bool isLoop)//上翻
//{
//    if(p == NULL)  return false;
//    int rowCount = p->model()->rowCount();
//    int rowHeight = p->rowHeight(0);
//    int tableViewHeight = p->height();
//    int rowCountPerPage = tableViewHeight/rowHeight-1;		//每页显示行数
//    int canNotViewCount = rowCount-rowCountPerPage;			//看不见的行数
//    if(canNotViewCount == 0) return false;
//    int maxValue = p->verticalScrollBar()->maximum();		// 当前SCROLLER最大显示值
//    if(maxValue == 0) return false;
//    int pageValue = (maxValue*rowCountPerPage)/canNotViewCount;
//    int nCurScroller = p->verticalScrollBar()->value();		//获得当前scroller值
//    if(nCurScroller>0)
//        p->verticalScrollBar()->setSliderPosition(nCurScroller-pageValue);
//    else
//    {
//        if(isLoop == TRUE)
//            p->verticalScrollBar()->setSliderPosition(maxValue);
//    }
//}
//bool Result::pageDown(QTableView *p,bool isLoop)//下翻
//{
//    if(p == NULL)  return false;
//    int rowCount = p->model()->rowCount();
//    int rowHeight = p->rowHeight(0);
//    int tableViewHeight = p->height();
//    int rowCountPerPage = tableViewHeight/rowHeight-1;		//每页显示行数
//    int canNotViewCount = rowCount-rowCountPerPage;			//看不见的行数
//    if(canNotViewCount == 0)
//        return false;
//    int maxValue = p->verticalScrollBar()->maximum();		// 当前SCROLLER最大显示值
//    if(maxValue == 0)
//        return false;
//    int pageValue = (maxValue*rowCountPerPage)/canNotViewCount;
//    int nCurScroller = p->verticalScrollBar()->value();		//获得当前scroller值
//    if(nCurScroller<maxValue)
//        p->verticalScrollBar()->setSliderPosition(nCurScroller+pageValue);
//    else
//    {
//        if(isLoop == TRUE)
//            p->verticalScrollBar()->setSliderPosition(0);
//    }
//}
//bool Result::pageHome(QTableView *p)//首页
//{
//    if(p == NULL)  return false;
//    int maxValue = p->verticalScrollBar()->maximum(); // 当前SCROLLER最大显示值
//    if(maxValue == 0)  return false;
//    p->verticalScrollBar()->setSliderPosition(0);
//}
//bool Result::pageEnd(QTableView *p)//末页
//{
//    if(p == NULL)  return false;
//    int maxValue = p->verticalScrollBar()->maximum(); // 当前SCROLLER最大显示值
//    if(maxValue == 0)  return false;
//    p->verticalScrollBar()->setSliderPosition(maxValue);
//}
