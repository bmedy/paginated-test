package io.github.bmedy.paginatedtest;

import java.util.List;
import java.util.stream.Collectors;

public class Page<T> {

    public int totalPage;
    public int totalElement;
    public int size;
    public int page;
    public List<T> data;

    public Page(final List<T> data, int size, int page) {
        this.page = page;
        this.totalElement = data.size();
        this.totalPage = (int) Math.ceil((double) this.totalElement / (double)size);
        if (size <= 0 || size > this.totalElement) {
            size = this.totalElement;
        } else {
            this.size = size;
        }
        this.page = page != 0 ? page : 1;

        this.data = data.stream().skip(this.page * this.size).limit(this.size).collect(Collectors.toList());
    }
}
