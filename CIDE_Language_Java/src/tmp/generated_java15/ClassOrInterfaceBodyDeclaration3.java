package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ClassOrInterfaceBodyDeclaration3 extends ClassOrInterfaceBodyDeclaration {
  public ClassOrInterfaceBodyDeclaration3(Modifiers modifiers1, EnumDeclaration enumDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers1", modifiers1),
      new PropertyOne<EnumDeclaration>("enumDeclaration", enumDeclaration)
    }, firstToken, lastToken);
  }
  public ClassOrInterfaceBodyDeclaration3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ClassOrInterfaceBodyDeclaration3(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers1() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers1")).getValue();
  }
  public EnumDeclaration getEnumDeclaration() {
    return ((PropertyOne<EnumDeclaration>)getProperty("enumDeclaration")).getValue();
  }
}
