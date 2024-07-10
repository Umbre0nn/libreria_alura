package alura.literalura.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexResponse {
    private int count;
    private String next;
    private String previous;
    private List<GutendexBook> results;

    // Getters y setters
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<GutendexBook> getResults() {
        return results;
    }

    public void setResults(List<GutendexBook> results) {
        this.results = results;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class GutendexBook {
        private int id;
        private String title;
        private List<Person> authors;
        private String media_type;
        private int download_count;

        // Getters y setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<Person> getAuthors() {
            return authors;
        }

        public void setAuthors(List<Person> authors) {
            this.authors = authors;
        }

        public String getMediaType() {
            return media_type;
        }

        public void setMediaType(String media_type) {
            this.media_type = media_type;
        }

        public int getDownloadCount() {
            return download_count;
        }

        public void setDownloadCount(int download_count) {
            this.download_count = download_count;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Person {
        private String name;
        private Integer birthYear;
        private Integer deathYear;

        // Getters y setters
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getBirthYear() {
            return birthYear;
        }

        public void setBirthYear(Integer birthYear) {
            this.birthYear = birthYear;
        }

        public Integer getDeathYear() {
            return deathYear;
        }

        public void setDeathYear(Integer deathYear) {
            this.deathYear = deathYear;
        }
    }
}
