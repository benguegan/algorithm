#include <bits/stdc++.h>
using namespace std;

auto print = [](const int &n)
{ cout << n << ' '; };

void printArray(vector<int> &arr)
{
  for_each(arr.cbegin(), arr.cend(), print);
  cout << "\n";
}

template <typename T>
vector<T> quickSort(vector<T> &arr)
{
  if (arr.size() <= 1)
    return arr;

  const int *pivot = &arr.front();
  vector<int> less;
  vector<int> greater;
  vector<int> sorted;

  for (const int *item = &arr.front(); item <= &arr.back(); item++)
  {
    if (item == pivot)
      continue;
    if (*item < *pivot)
      less.push_back(*item);
    else
      greater.push_back(*item);
  }

  less = quickSort(less);
  greater = quickSort(greater);

  sorted = less;
  sorted.push_back(*pivot);
  sorted.insert(sorted.end(), greater.begin(), greater.end());

  printArray(sorted);
  return sorted;
}

int main()
{
  int n;
  cin >> n;

  vector<int> arr(n);
  for (int i = 0; i < (int)n; ++i)
  {
    cin >> arr[i];
  }

  quickSort(arr);

  return 0;
}
