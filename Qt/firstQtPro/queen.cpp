#include "queen.h"
#include <list>
#include <map>

Queen::Queen()
{
}
void Queen::clear()
{
   blackList.clear();
   results.clear();
   rows = 0;
}

void Queen::init() //初始化
{
    matrix = new int[n * n];
    position = new int[n];
    for (int i = 0; i < n; i++)
    {
        position[i] = -1;
    }
}

void Queen::findQueen(int num)
{
    n = num;
    init();
    int i;
    while (rows != -1) //当深度回到最上面时返回
    {
        if (pointer_blackList == NULL) //当前行黑名单为空
        {
            setBlackList(); //搜索黑名单
        }
        for (i = 0; i < n; i++) //遍历黑名单
        {
            if (pointer_blackList[i] == 0) //发现可以放皇后
            {
                pointer_blackList[i] = 1; //赋值为1
                break;
            }
        }
        if (i == n) //黑名单遍历完毕还没有位置
        {
            delete[] pointer_blackList; //删除
            rows--;
            if (rows == -1)
            {
                break;
            }
            pointer_blackList = blackList[rows]; //返回上一层
            continue;
        }
        position[rows] = i;
        pointer_blackList = NULL; //放了一个后不再指向当前黑名单
        if (rows == n - 1)        //&& position[n - 1] != -1 出现解然后往上走
        {
            for (int i = 0; i < n; i++)
            {
                for (int j = 0; j < n; j++)
                {
                    matrix[i * n + j] = 0;
                }
                matrix[i * n + position[i]] = 1;
            }
            results.push_back(matrix); //将position中的皇后位置储存
            matrix = new int[n * n];   //开辟新的内存空间
            delete[] blackList[rows];
            rows--;
            pointer_blackList = blackList[rows];
        }
        else
        {
            rows++;
        }
    }
    delete matrix;
    matrix = NULL;
    delete pointer_blackList;
    pointer_blackList = NULL;
}

void Queen::setBlackList() //每次改变皇后位置时修改非法位置
{
    pointer_blackList = new int[n]; //new一个新的黑名单
    for (int i = 0; i < n; i++)     //初始化
    {
        pointer_blackList[i] = 0;
    }
    int col;
    for (int row = 0; row < rows; row++) //遍历深度数组
    {

        col = position[row];
        pointer_blackList[col] = 1; //在同一列
        int sum = row + col;
        int minus = row - col;
        int col_1 = sum - rows;
        int col_2 = rows - minus;
        if (col_1 >= 0 && col_1 < n) //在同一正斜线
        {
            pointer_blackList[col_1] = 1;
        }
        if (col_2 >= 0 && col_2 < n) //在同一反斜线
        {
            pointer_blackList[col_2] = 1;
        }
    }
    blackList[rows] = pointer_blackList; //将指针赋值到map中指针
}
void Queen::calculateIt(int num)
{
    clear();
    findQueen(num);
}
std::list<int *> Queen::getResult()
{
    return results;
}
