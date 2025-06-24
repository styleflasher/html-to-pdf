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
- Creating a tag with format `v*` (e.g., `v1.0.0`) will:
  - Update the version in the project
  - Create a GitHub release
  - Attach the built JAR files to the release

### Creating a Release

To create a new release:

1. Create and push a new tag with the version number:
   ```bash
   git tag v1.0.0
   git push origin v1.0.0
   ```

2. The GitHub Actions workflow will automatically:
   - Update the version in pom.xml and source code
   - Build the project
   - Create a GitHub release with the JAR files
