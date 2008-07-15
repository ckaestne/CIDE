package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AllocationExpressionInit2 extends AllocationExpressionInit {
  public AllocationExpressionInit2(Arguments arguments, ClassOrInterfaceBody classOrInterfaceBody, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Arguments>("arguments", arguments),
      new PropertyZeroOrOne<ClassOrInterfaceBody>("classOrInterfaceBody", classOrInterfaceBody)
    }, firstToken, lastToken);
  }
  public AllocationExpressionInit2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AllocationExpressionInit2(cloneProperties(),firstToken,lastToken);
  }
  public Arguments getArguments() {
    return ((PropertyOne<Arguments>)getProperty("arguments")).getValue();
  }
  public ClassOrInterfaceBody getClassOrInterfaceBody() {
    return ((PropertyZeroOrOne<ClassOrInterfaceBody>)getProperty("classOrInterfaceBody")).getValue();
  }
}
