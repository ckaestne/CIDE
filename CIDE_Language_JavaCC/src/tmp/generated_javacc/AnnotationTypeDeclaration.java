package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnnotationTypeDeclaration extends GenASTNode {
  public AnnotationTypeDeclaration(JavaIdentifier javaIdentifier, AnnotationTypeBody annotationTypeBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyOne<AnnotationTypeBody>("annotationTypeBody", annotationTypeBody)
    }, firstToken, lastToken);
  }
  public AnnotationTypeDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnnotationTypeDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public AnnotationTypeBody getAnnotationTypeBody() {
    return ((PropertyOne<AnnotationTypeBody>)getProperty("annotationTypeBody")).getValue();
  }
}
