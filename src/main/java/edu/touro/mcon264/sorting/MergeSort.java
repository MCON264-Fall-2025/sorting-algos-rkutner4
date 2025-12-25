package edu.touro.mcon264.sorting;

import java.util.Comparator;

public class MergeSort implements Sorter {

    @Override
    public <T> void sort(T[] a, Comparator<? super T> comp) {
        if (a == null || a.length <= 1) {
            return; // already sorted
        }
        mergeSort(a, 0, a.length - 1, comp);
    }

    private <T> void mergeSort(T[] a, int left, int right, Comparator<? super T> comp) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(a, left, mid, comp);
        mergeSort(a, mid + 1, right, comp);
        merge(a, left, mid, right, comp);
    }

    private <T> void merge(T[] a, int left, int mid, int right, Comparator<? super T> comp) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Object[] leftArr = new Object[n1];
        Object[] rightArr = new Object[n2];

        for (int i = 0; i < n1; i++) {
            leftArr[i] = a[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = a[mid + 1 + j];
        }

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            @SuppressWarnings("unchecked")
            T leftVal = (T) leftArr[i];
            @SuppressWarnings("unchecked")
            T rightVal = (T) rightArr[j];

            if (comp.compare(leftVal, rightVal) <= 0) {
                a[k++] = leftVal;
                i++;
            } else {
                a[k++] = rightVal;
                j++;
            }
        }

        while (i < n1) {
            @SuppressWarnings("unchecked")
            T val = (T) leftArr[i++];
            a[k++] = val;
        }

        while (j < n2) {
            @SuppressWarnings("unchecked")
            T val = (T) rightArr[j++];
            a[k++] = val;
        }
    }
}
