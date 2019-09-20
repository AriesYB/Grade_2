#ifndef QUEEN_H
#define QUEEN_H
#include <list>
#include <map>

class Queen
{
public:
    Queen();
    void calculateIt(int num);
    std::list<int *> getResult();
private:
    int n; //皇后数量
    int *matrix;               //当前棋盘
    int *position;             //各皇后列位置
    int *pointer_blackList;    //当前行黑名单的指针
    int rows = 0;              //当前行数 该插第几行
    std::map<int, int *> blackList; //遍历到每一行时的黑名单(之前的那些和当前的)
    std::list<int *> results;       //解集
    void findQueen(int num);
    void setBlackList();
    void clear();
    void init();
};

#endif // QUEEN_H
