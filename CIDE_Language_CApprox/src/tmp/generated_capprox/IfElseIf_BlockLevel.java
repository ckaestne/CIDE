package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class IfElseIf_BlockLevel extends GenASTNode {
  public IfElseIf_BlockLevel(IfElseIf ifElseIf, ASTStringNode findlineend, Sequence_CodeUnit_InBlock sequence_CodeUnit_InBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<IfElseIf>("ifElseIf", ifElseIf),
      new PropertyOne<ASTStringNode>("findlineend", findlineend),
      new PropertyOne<Sequence_CodeUnit_InBlock>("sequence_CodeUnit_InBlock", sequence_CodeUnit_InBlock)
    }, firstToken, lastToken);
  }
  public IfElseIf_BlockLevel(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new IfElseIf_BlockLevel(cloneProperties(),firstToken,lastToken);
  }
  public IfElseIf getIfElseIf() {
    return ((PropertyOne<IfElseIf>)getProperty("ifElseIf")).getValue();
  }
  public ASTStringNode getFindlineend() {
    return ((PropertyOne<ASTStringNode>)getProperty("findlineend")).getValue();
  }
  public Sequence_CodeUnit_InBlock getSequence_CodeUnit_InBlock() {
    return ((PropertyOne<Sequence_CodeUnit_InBlock>)getProperty("sequence_CodeUnit_InBlock")).getValue();
  }
}
