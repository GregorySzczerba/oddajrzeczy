package com.example.demo.Category;

import com.example.demo.foundation.Foundation;

import javax.persistence.*;
import java.util.List;

@Entity
    @Table(name = "categories")
    public class Category {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @OneToMany
        @JoinColumn(name = "category")
        private List<Foundation> foundation;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Foundation> getFoundation() {
            return foundation;
        }

        public void setFoundation(List<Foundation> foundation) {
            this.foundation = foundation;
        }

        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

