package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeDeclaration4 extends TypeDeclaration {
  public TypeDeclaration4(Modifiers modifiers2, AnnotationTypeDeclaration annotationTypeDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers2", modifiers2),
      new PropertyOne<AnnotationTypeDeclaration>("annotationTypeDeclaration", annotationTypeDeclaration)
    }, firstToken, lastToken);
  }
  public TypeDeclaration4(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeDeclaration4(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers2() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers2")).getValue();
  }
  public AnnotationTypeDeclaration getAnnotationTypeDeclaration() {
    return ((PropertyOne<AnnotationTypeDeclaration>)getProperty("annotationTypeDeclaration")).getValue();
  }
}
