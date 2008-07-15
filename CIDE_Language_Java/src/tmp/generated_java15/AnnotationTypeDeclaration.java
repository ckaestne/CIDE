package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnnotationTypeDeclaration extends GenASTNode {
  public AnnotationTypeDeclaration(ASTStringNode identifier, AnnotationTypeBody annotationTypeBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<AnnotationTypeBody>("annotationTypeBody", annotationTypeBody)
    }, firstToken, lastToken);
  }
  public AnnotationTypeDeclaration(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnnotationTypeDeclaration(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public AnnotationTypeBody getAnnotationTypeBody() {
    return ((PropertyOne<AnnotationTypeBody>)getProperty("annotationTypeBody")).getValue();
  }
}
