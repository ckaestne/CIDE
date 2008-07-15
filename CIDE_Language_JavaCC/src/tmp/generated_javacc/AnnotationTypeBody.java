package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnnotationTypeBody extends GenASTNode {
  public AnnotationTypeBody(ArrayList<AnnotationTypeMemberDeclaration> annotationTypeMemberDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrMore<AnnotationTypeMemberDeclaration>("annotationTypeMemberDeclaration", annotationTypeMemberDeclaration)
    }, firstToken, lastToken);
  }
  public AnnotationTypeBody(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnnotationTypeBody(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<AnnotationTypeMemberDeclaration> getAnnotationTypeMemberDeclaration() {
    return ((PropertyZeroOrMore<AnnotationTypeMemberDeclaration>)getProperty("annotationTypeMemberDeclaration")).getValue();
  }
}
