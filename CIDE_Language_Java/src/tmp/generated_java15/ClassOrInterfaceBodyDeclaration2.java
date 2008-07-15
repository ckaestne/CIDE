package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceBodyDeclaration2 extends ClassOrInterfaceBodyDeclaration {
  public ClassOrInterfaceBodyDeclaration2(Modifiers modifiers, ClassOrInterfaceDeclaration classOrInterfaceDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers", modifiers),
      new PropertyOne<ClassOrInterfaceDeclaration>("classOrInterfaceDeclaration", classOrInterfaceDeclaration)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceBodyDeclaration2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceBodyDeclaration2(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers")).getValue();
  }
  public ClassOrInterfaceDeclaration getClassOrInterfaceDeclaration() {
    return ((PropertyOne<ClassOrInterfaceDeclaration>)getProperty("classOrInterfaceDeclaration")).getValue();
  }
}
