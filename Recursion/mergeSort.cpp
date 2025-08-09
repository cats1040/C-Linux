#include <bits/stdc++.h>
using namespace std;

void merge(vector<int> &a, int l, int mid, int r, vector<int> &aux)
{
    int i = l, j = mid + 1;

    for (int k = l; k < r + 1; k++)
    {
        if (i > mid)
        {
            aux[k] = a[j];
            j++;
        }
        else if (j > r)
        {
            aux[k] = a[i];
            i++;
        }
        else if (a[i] > a[j])
        {
            aux[k] = a[j];
            j++;
        }
        else
        {
            aux[k] = a[i];
            i++;
        }
    }

    for (int k = l; k < r + 1; k++)
    {
        a[k] = aux[k];
    }
}

void mergeSort(vector<int> &a, int l, int r, vector<int> &aux)
{
    if (l >= r)
        return;

    int mid = l + (r - l) / 2;

    mergeSort(a, l, mid, aux);
    mergeSort(a, mid + 1, r, aux);

    merge(a, l, mid, r, aux);
}

int main()
{
    vector<int> a = {3, 2, 5, 7, 8, 2, 8, 18, 11};

    vector<int> aux(a.size());

    mergeSort(a, 0, a.size() - 1, aux);

    for (auto i : a)
        cout << i << " ";

    return 0;
}