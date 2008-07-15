package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeDeclaration2 extends TypeDeclaration {
  public TypeDeclaration2(Modifiers modifiers, ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers", modifiers),
      new PropertyOne<ClassOrInterfaceDeclaration>("classOrInterfaceDeclaration", classOrInterfaceDeclaration)
    }, firstToken, lastToken);
  }
  public TypeDeclaration2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeDeclaration2(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers")).getValue();
  }
  public ClassOrInterfaceDeclaration getClassOrInterfaceDeclaration() {
    return ((PropertyOne<ClassOrInterfaceDeclaration>)getProperty("classOrInterfaceDeclaration")).getValue();
  }
}
