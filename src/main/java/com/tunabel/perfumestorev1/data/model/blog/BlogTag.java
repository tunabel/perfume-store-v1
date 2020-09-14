package com.tunabel.perfumestorev1.data.model.blog;


import javax.persistence.*;

@Entity
@Table(name = "dbo_blog_tag")
public class BlogTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "blog_tag_id")
    int id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "blog_id")
    Blog blog;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tag_id")
    Tag tag;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
