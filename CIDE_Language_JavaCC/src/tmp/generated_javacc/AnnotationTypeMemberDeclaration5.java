package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnnotationTypeMemberDeclaration5 extends AnnotationTypeMemberDeclaration {
  public AnnotationTypeMemberDeclaration5(Modifiers modifiers4, FieldDeclaration fieldDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers4", modifiers4),
      new PropertyOne<FieldDeclaration>("fieldDeclaration", fieldDeclaration)
    }, firstToken, lastToken);
  }
  public AnnotationTypeMemberDeclaration5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnnotationTypeMemberDeclaration5(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers4() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers4")).getValue();
  }
  public FieldDeclaration getFieldDeclaration() {
    return ((PropertyOne<FieldDeclaration>)getProperty("fieldDeclaration")).getValue();
  }
}
