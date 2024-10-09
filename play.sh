sbt compile && sbt assembly && python eval_setup/copy_pisa_jars.py --pisa-jar-path target/scala-2.13/PISA-assembly-0.1.jar --number-of-jars $N --output-path $OUTPUT_PATH


bash scripts/extraction/script_0.sh

python3 src/main/python/prepare_episodic_transitions.py \
    -efd afp_extractions_aj -sd data/proof_and_state_aj \
    --proof --state

python src/main/python/one_stage_extraction.py \
  --isa-path /home/user/Isabelle2022 \
  -wd /home/user/afp-2022-12-06/thys/Example-Inter \
  -tfp /home/user/afp-2022-12-06/thys/Example-Inter/Inter.thy \
  --saving-directory /home/user/lhx/llama_marcel/play/no_metis_extract \
  -p 8199

python src/main/python/one_stage_extraction.py \
  --isa-path /home/user/Isabelle2022 \
  -wd /home/user/afp-2022-12-06/thys/Example-Submission \
  -tfp /home/user/afp-2022-12-06/thys/Example-Submission/Submission.thy \
  --saving-directory /home/user/lhx/llama_marcel/play/sub_extract \
  -p 8199



python3 src/main/python/prepare_episodic_transitions.py \
    -efd /home/user/lhx/llama_marcel/play/no_metis_extract \
    -sd /home/user/lhx/llama_marcel/play/proof_and_states \
    --proof --state

PORT=8199
sbt "runMain pisa.server.PisaOneStageServer$PORT"