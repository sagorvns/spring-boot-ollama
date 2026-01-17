package System_Design;

import java.util.*;

public class FileSystem {

    enum Permission {
        READ, WRITE, EXECUTE
    }

    // Base Component
    abstract static class FileComponent {
        protected String name;
        protected Set<Permission> permissions = EnumSet.noneOf(Permission.class);

        public FileComponent(String name) {
            this.name = name;
        }

        public abstract void show(int indent);

        public void setPermission(Permission p) {
            permissions.add(p);
        }

        public boolean hasPermission(Permission p) {
            return permissions.contains(p);
        }

        public abstract void add(FileComponent component);
        public abstract void remove(FileComponent component);
        public abstract boolean isDirectory();
    }

    // Leaf – File
    static class FileLeaf extends FileComponent {

        public FileLeaf(String name) {
            super(name);
        }

        @Override
        public void show(int indent) {
            System.out.println(" ".repeat(indent) + "- File: " + name + " | Perms: " + permissions);
        }

        @Override
        public void add(FileComponent component) {
            throw new UnsupportedOperationException("Cannot add to file");
        }

        @Override
        public void remove(FileComponent component) {
            throw new UnsupportedOperationException("Cannot remove from file");
        }

        @Override
        public boolean isDirectory() {
            return false;
        }
    }

    // Composite – Directory
    static class DirectoryComposite extends FileComponent {
        private List<FileComponent> children = new ArrayList<>();

        public DirectoryComposite(String name) {
            super(name);
        }

        @Override
        public void show(int indent) {
            System.out.println(" ".repeat(indent) + "+ Dir: " + name + " | Perms: " + permissions);
            for (FileComponent child : children) {
                child.show(indent + 2);
            }
        }

        @Override
        public void add(FileComponent component) {
            children.add(component);
        }

        @Override
        public void remove(FileComponent component) {
            children.remove(component);
        }

        @Override
        public boolean isDirectory() {
            return true;
        }
    }

    // Demo
    public static void main(String[] args) {
        DirectoryComposite root = new DirectoryComposite("root");
        root.setPermission(Permission.READ);
        root.setPermission(Permission.WRITE);

        DirectoryComposite home = new DirectoryComposite("home");
        home.setPermission(Permission.READ);

        DirectoryComposite user = new DirectoryComposite("user");
        FileLeaf file1 = new FileLeaf("resume.pdf");
        file1.setPermission(Permission.READ);

        FileLeaf file2 = new FileLeaf("script.sh");
        file2.setPermission(Permission.READ);
        file2.setPermission(Permission.EXECUTE);

        user.add(file1);
        user.add(file2);

        home.add(user);
        root.add(home);

        root.show(0);
    }
}

