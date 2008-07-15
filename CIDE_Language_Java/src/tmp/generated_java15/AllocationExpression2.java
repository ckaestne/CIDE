package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AllocationExpression2 extends AllocationExpression {
  public AllocationExpression2(ClassOrInterfaceType classOrInterfaceType, TypeArguments typeArguments, AllocationExpressionInit allocationExpressionInit, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ClassOrInterfaceType>("classOrInterfaceType", classOrInterfaceType),
      new PropertyZeroOrOne<TypeArguments>("typeArguments", typeArguments),
      new PropertyOne<AllocationExpressionInit>("allocationExpressionInit", allocationExpressionInit)
    }, firstToken, lastToken);
  }
  public AllocationExpression2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AllocationExpression2(cloneProperties(),firstToken,lastToken);
  }
  public ClassOrInterfaceType getClassOrInterfaceType() {
    return ((PropertyOne<ClassOrInterfaceType>)getProperty("classOrInterfaceType")).getValue();
  }
  public TypeArguments getTypeArguments() {
    return ((PropertyZeroOrOne<TypeArguments>)getProperty("typeArguments")).getValue();
  }
  public AllocationExpressionInit getAllocationExpressionInit() {
    return ((PropertyOne<AllocationExpressionInit>)getProperty("allocationExpressionInit")).getValue();
  }
}
