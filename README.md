# html-to-pdf

Convert HTML into PDF/ua.

## Usage

```bash
java -jar html-to-pdf-<version>-jar-with-dependencies.jar <input-html-file> -o <output-pdf-file>
```

## Building

This project uses Maven for building:

```bash
mvn clean package
```

This will create two JAR files in the `target` directory:
- `html-to-pdf-<version>.jar`: The main JAR file
- `html-to-pdf-<version>-jar-with-dependencies.jar`: A fat JAR with all dependencies included

## Continuous Integration

This project uses GitHub Actions for continuous integration:
- Every push to the main branch and pull requests will trigger a build
- Creating a tag with format `v*` (e.g., `v2025.07.1`) will:
  - Update the version in the project
  - Create a GitHub release
  - Attach the built JAR files to the release

### Creating a Release

To create a new release:

1. Create and push a new tag with the version number:
   ```bash
   git tag v2025.07.1
   git push origin v2025.07.1
   ```

2. The GitHub Actions workflow will automatically:
   - Update the version in pom.xml and source code
   - Build the project
   - Create a GitHub release with the JAR files
