#include <bits/stdc++.h>
using namespace std;

int merge(int l, int mid, int r, vector<int> &arr, vector<int> &aux)
{
    int i = l, j = mid + 1, inv = 0;

    for (int k = l; k < r + 1; k++)
    {
        if (i > mid)
        {
            aux[k] = arr[j];
            j++;
        }
        else if (j > r)
        {
            aux[k] = arr[i];
            i++;
        }
        else if (arr[i] > arr[j])
        {
            aux[k] = arr[j];
            j++;
            // cout << "here ";
            inv++;
        }
        else
        {
            aux[k] = arr[i];
            i++;
        }
    }

    for (int k = l; k < r + 1; k++)
    {
        arr[k] = aux[k];
    }

    return inv;
}

int countInversions(vector<int> &arr, vector<int> &aux, int l, int r)
{
    if (l >= r)
    {
        return 0;
    }

    int mid = l + (r - l) / 2;
    int inv = 0;

    inv += countInversions(arr, aux, l, mid);
    inv += countInversions(arr, aux, mid + 1, r);

    inv += merge(l, mid, r, arr, aux);

    return inv;
}

int main()
{
    vector<int> a = {3, 2, 5, 7, 100, 99, -9, 5, 7, 8, 2, 8, 18, 11};

    vector<int> aux(a.size());

    int inv = countInversions(a, aux, 0, a.size() - 1);

    cout << inv << "\n";

    return 0;
}
