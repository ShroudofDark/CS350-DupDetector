#include <iostream>
#include <iomanip>

using namespace std;

int test();

int main()
{
     cout << setfill('*');
    cout << "12345678901234567890" << endl;
    cout << setw(5) << "18" << setw(7) << "Happy"
     << setw(8) << "Sleepy" << endl;
    return 0;
}

int test() {

    int y = 100;
    return 37, y, 2 * 3;
}
