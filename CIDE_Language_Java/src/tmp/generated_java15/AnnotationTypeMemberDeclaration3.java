package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnnotationTypeMemberDeclaration3 extends AnnotationTypeMemberDeclaration {
  public AnnotationTypeMemberDeclaration3(Modifiers modifiers2, EnumDeclaration enumDeclaration, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers2", modifiers2),
      new PropertyOne<EnumDeclaration>("enumDeclaration", enumDeclaration)
    }, firstToken, lastToken);
  }
  public AnnotationTypeMemberDeclaration3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnnotationTypeMemberDeclaration3(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers2() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers2")).getValue();
  }
  public EnumDeclaration getEnumDeclaration() {
    return ((PropertyOne<EnumDeclaration>)getProperty("enumDeclaration")).getValue();
  }
}
