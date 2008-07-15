package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeDeclaration3 extends TypeDeclaration {
  public TypeDeclaration3(Modifiers modifiers1, EnumDeclaration enumDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers1", modifiers1),
      new PropertyOne<EnumDeclaration>("enumDeclaration", enumDeclaration)
    }, firstToken, lastToken);
  }
  public TypeDeclaration3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeDeclaration3(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers1() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers1")).getValue();
  }
  public EnumDeclaration getEnumDeclaration() {
    return ((PropertyOne<EnumDeclaration>)getProperty("enumDeclaration")).getValue();
  }
}
