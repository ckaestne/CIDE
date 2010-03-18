package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AnnotationTypeMemberDeclaration1 extends AnnotationTypeMemberDeclaration {
  public AnnotationTypeMemberDeclaration1(Modifiers modifiers, Type type, ASTStringNode identifier, DefaultValue defaultValue, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Modifiers>("modifiers", modifiers),
      new PropertyOne<Type>("type", type),
      new PropertyOne<ASTStringNode>("identifier", identifier),
      new PropertyZeroOrOne<DefaultValue>("defaultValue", defaultValue)
    }, firstToken, lastToken);
  }
  public AnnotationTypeMemberDeclaration1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AnnotationTypeMemberDeclaration1(cloneProperties(),firstToken,lastToken);
  }
  public Modifiers getModifiers() {
    return ((PropertyOne<Modifiers>)getProperty("modifiers")).getValue();
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public DefaultValue getDefaultValue() {
    return ((PropertyZeroOrOne<DefaultValue>)getProperty("defaultValue")).getValue();
  }
}
