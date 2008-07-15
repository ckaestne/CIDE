package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceBodyDeclaration5 extends ClassOrInterfaceBodyDeclaration {
  public ClassOrInterfaceBodyDeclaration5(Modifiers modifiers3, FieldDeclaration fieldDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers3", modifiers3),
      new PropertyOne<FieldDeclaration>("fieldDeclaration", fieldDeclaration)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceBodyDeclaration5(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceBodyDeclaration5(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers3() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers3")).getValue();
  }
  public FieldDeclaration getFieldDeclaration() {
    return ((PropertyOne<FieldDeclaration>)getProperty("fieldDeclaration")).getValue();
  }
}
