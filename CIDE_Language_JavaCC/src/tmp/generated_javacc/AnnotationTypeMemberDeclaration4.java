package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnnotationTypeMemberDeclaration4 extends AnnotationTypeMemberDeclaration {
  public AnnotationTypeMemberDeclaration4(Modifiers modifiers3, AnnotationTypeDeclaration annotationTypeDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers3", modifiers3),
      new PropertyOne<AnnotationTypeDeclaration>("annotationTypeDeclaration", annotationTypeDeclaration)
    }, firstToken, lastToken);
  }
  public AnnotationTypeMemberDeclaration4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnnotationTypeMemberDeclaration4(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers3() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers3")).getValue();
  }
  public AnnotationTypeDeclaration getAnnotationTypeDeclaration() {
    return ((PropertyOne<AnnotationTypeDeclaration>)getProperty("annotationTypeDeclaration")).getValue();
  }
}
